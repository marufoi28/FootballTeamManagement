SELECT
    p.player_id
    , p.first_name
    , p.last_name
    , p.first_name_kana
    , p.last_name_kana
    , p.birth_date
    , p.position_id
    , pos.position_name
    , pos.position_classification
    , p.uniform_number
    , p.has_license
    , p.is_student
    , p.created_at
    , p.updated_at
