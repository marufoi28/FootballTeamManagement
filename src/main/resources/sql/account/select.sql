SELECT
    USER_ID
    , PASS
    , MAIL
    , NAME
    , AGE 
FROM
    ACCOUNTS 
WHERE
    USER_ID = ? 
    AND PASS = ?
