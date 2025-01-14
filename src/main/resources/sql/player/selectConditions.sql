FROM
    Players p 
    LEFT JOIN Positions pos 
        ON p.position_id = pos.position_id
WHERE
    p.skj_flg = '0'
