docker build --no-cache -f Dockerfiles/Dockerfile.App -t javagisodatasample/app ../JavaGISOdataBackend

docker build --no-cache -f Dockerfiles/Dockerfile.Postgres -t javagisodatasample/postgres ../SQL

docker build --no-cache -f Dockerfiles/Dockerfile.Geoserver -t javagisodatasample/geoserver .