//backup terraform state
terraform {
  backend "s3" {
    bucket = "restaurant-api-devops-tfstate"
    key = "restaurant-webapp.tfstate"
    region = "us-east-2"
    encrypt = true
    dynamodb_table = "restaurant-api-devops-tfstate-lock"
//    profile = "ilkin.mehdiyev"
//    shared_credentials_file = "~/.aws/config"
  }
}

// provider for terraform
provider "aws" {
  region = "us-east-2"
  profile = "ilkin.mehdiyev"
//  version = "~> 3.25.0"
}

