SELECT
    m.match_id
    , m.field_id
    , m.event_date
    , YEAR(m.event_date) AS year
    , MONTH(m.event_date) AS month
    , DAY(m.event_date) AS day
    , m.event_start_time
    , HOUR(m.event_start_time) AS hour
    , MINUTE(m.event_start_time) AS minute
    , m.opponent_id
    , m.our_score
    , m.opponent_score
    , f.field_name
    , f.address_id
    , o.opponent_id
    , o.opponent_name 
    , a.prefecture
    , a.prefecture_en
    , a.city
    , a.street_address
    , a.locale
FROM
    `MATCH` m 
    LEFT OUTER JOIN FIELD f 
        ON m.field_id = f.field_id 
    LEFT OUTER JOIN OPPONENT o 
        ON m.opponent_id = o.opponent_id
    LEFT OUTER JOIN ADDRESS a
        ON a.address_id = f.field_id
ORDER BY m.event_date ASC