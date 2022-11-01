# billing
KATA PACIFICA backend

# Description
Un projet SpringBoot qui expose une API rest permettant de récupérer les factures sauvegardées dans une base H2 et calculer les taxes sur les divers produits.
La base est préremplie par trois factures IDs: 1, 2 et 3.

# Docker
build image: docker image build -t khila/gsd:billing-ctr .
push image: docker image push khila/gsd:billing-ctr
remove local image: docker image rm khila/gsd:billing-ctr
run container: docker container run -d --name api -p 8080:8080 khila/gsd:billing-ctr
stop container: docker container stop api
