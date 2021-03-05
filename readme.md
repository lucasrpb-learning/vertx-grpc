#gcloud builds submit --tag gcr.io/fir-91406/grpc

docker build .
docker tag ad06537ac50e gcr.io/fir-91406/grpc
docker push gcr.io/fir-91406/grpc

Enable http/2 on deployment of cloud run (connections)!

#gcloud run services update grpc --use-http2 