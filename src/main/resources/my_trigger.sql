create or replace function user_account_trigger()
    returns trigger
    language plpgsql
as
$$
declare
BEGIN
    insert into account (amount, created_at, currency_id, user_id)
    values (0, now(), 10, new.id);
    return new;
END
$$;

create or replace trigger account_trigger
    after insert
    on users
    for each row
execute function user_account_trigger();