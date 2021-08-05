//backup terraform state
terraform {
  backend "s3" {
    bucket = "restaurant-api-devops-tfstate"
    key = "restaurant-webapp.tfstate"
    region = "us-east-2"
    encrypt = true
    dynamodb_table = "restaurant-api-devops-tfstate-lock"
//    access_key = "AKIAQEQR7I6VKE4WKWPQ"
//    secret_key = "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"
  }
}

// provider for terraform
provider "aws" {
  region = "us-east-2"
//  version = "~> 3.25.0"
}

