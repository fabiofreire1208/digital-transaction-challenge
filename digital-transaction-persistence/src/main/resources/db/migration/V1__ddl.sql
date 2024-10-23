CREATE TABLE IF NOT EXISTS transactions_history(
  id UUID NOT NULL PRIMARY KEY,
  account_from_id UUID NOT NULL,
  account_to_id UUID NOT NULL,
  amount NUMERIC NOT NULL,
  transaction_type VARCHAR(50),
  transaction_status VARCHAR(50),
  transaction_creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  transaction_update_date TIMESTAMP
);