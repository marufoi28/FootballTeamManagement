package servlet.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.player.AddPlayer;
import service.file.FileService;

@MultipartConfig
public class ImportCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("csvFile");
		
		request.setCharacterEncoding("UTF-8");
		
		if(filePart == null) {
			request.setAttribute("fileOperationMessage", "CSVファイルが選択されていません");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/player/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		InputStream fileContent = filePart.getInputStream();
		
		try(@SuppressWarnings("deprecation")
		CSVParser csvParser = new CSVParser(new InputStreamReader(fileContent, "UTF-8"), CSVFormat.DEFAULT.withFirstRecordAsHeader())){
			Iterator<CSVRecord> iterator = csvParser.iterator();
			FileService service = new FileService();
			
				List<AddPlayer> players = new ArrayList<>();
				
				while(iterator.hasNext()) {
					CSVRecord record = iterator.next();
					
					AddPlayer player = new AddPlayer();
					player.setFirstName(record.get("first_name"));
					player.setLastName(record.get("last_name"));
					player.setFirstNameKana(record.get("first_name_kana"));
					player.setLastNameKana(record.get("last_name_kana"));
					player.setBirthDate(LocalDate.parse(record.get("birth_date")));
					player.setPositionId(Integer.parseInt(record.get("position_id")));
					player.setUniformNumber(Integer.parseInt(record.get("uniform_number")));
					player.setHasLicense(Boolean.parseBoolean(record.get("has_license")));
					player.setIsStudent(Boolean.parseBoolean(record.get("is_student")));
					players.add(player);
				}
			service.importCSVPlayers(players);
			request.getSession().setAttribute("successMessage", "ファイルのインポートに成功しました。");
		} catch(Exception e) {
    		request.getSession().setAttribute("errorMessage", "CSVファイルの解析中にエラーが発生しました: " + e.getMessage());
		} finally {
			response.sendRedirect("SearchPlayerServlet");
		}
	}
}