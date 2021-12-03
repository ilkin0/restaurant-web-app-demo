aws ecr get-login-password --region us-west-2 |
  docker login --username AWS --password-stdin XXYYXXYYXXYY.dkr.ecr.us-west-2.amazonaws.com &&
  docker push XXYYXXYYXXYY.dkr.ecr.us-west-2.amazonaws.com/test-sns-stack
