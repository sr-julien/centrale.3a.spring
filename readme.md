# ROYANT Killian - Projet Spring - Gallerie James Webb

![Gallerie](screenshot.png)

## Description

Ce projet est une application web permettant de **gérer une simple gallerie d'images**. Cette application permet d'ajouter des images à des albums. Il est possible d'importer des nouvelles photos à partir d'URL. Ces URL seront stockées dans une base de données et seront affichées sur la page d'accueil.

L'application fournit également la possibilité de supprimer les images et les albums. On peut également créer des nouveaux albums.

Ce projet utilise le framework Java Spring Boot. Les URLs sont stockées dans une base de données gérée à l'aide de PostgreSQL.

## Étapes

### Prérequis

- IntelliJ IDEA (ou autre IDE permettant de gérer des projets Gradle)
- Spring Boot
- PostgreSQL

### Installation

1. Cloner le projet
2. Créer une base de données PostgreSQL *(de base, le nom d'utilisateur est "postgres" et le mot de passe est "admin")*
3. Configurer le fichier application.properties *(si besoin)*
4. Connecter la base de données à IntelliJ IDEA
5. Lancer le projet
6. Ouvrir le fichier index.html puis tester les différentes fonctionnalités

Le projet initialisera automatiquement la base de données avec des données de test. Ces données peuvent être réinitialisées à l'aide du bouton "reset" sur la page d'accueil. Les nouveaux albums sont également créés avec une image de test qui peut être supprimée après la création.

Pour accéder à la page d'un album à partir d'un URL, il faut fournir son id comme ceci : `./album.html?id=27` (attention : les id ne commencent pas forcément à 1)

## Tester l'API

Les routes existantes sont les suivantes :

- `GET /getAlbumName/{id}` : récupère le nom de l'album correspondant à l'ID
- `GET /getAlbum/{id}` : récupère l'album correspondant à l'ID
- `GET /getAlbums` : récupère tous les albums
- `GET /addAlbum/{name}` : ajoute un album avec le nom spécifié
- `POST /addImage/{id}` : ajoute une image à l'album correspondant à l'ID *(le corps de la requête doit contenir l'URL de l'image)*
- `GET /init` : vide la base de données
- `DELETE /deleteAlbum/{id}` : supprime l'album correspondant à l'ID (et ses images)
- `DELETE /deleteImage/{id}` : supprime l'image correspondant à l'ID
