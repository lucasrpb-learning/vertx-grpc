#gcloud builds submit --tag gcr.io/<project>/grpc

docker build .
docker tag ad06537ac50e gcr.io/<project>/grpc
docker push gcr.io/<project>/grpc

Enable http/2 on deployment of cloud run (connections)!

#gcloud run services update grpc --use-http2 