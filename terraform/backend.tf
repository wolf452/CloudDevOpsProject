terraform {
  backend "s3" {
    bucket         = "test-final-bucket-1"
    key            = "terraform/state.tfstate"
    region         = "us-east-1"
    encrypt        = true
  }
}
resource "aws_dynamodb_table" "terraform_state_lock" {
  name           = "ivolvegp-table"
  billing_mode   = "PAY_PER_REQUEST"
  hash_key       = "LockID"

  attribute {
    name = "LockID"
    type = "S"
  }
}
