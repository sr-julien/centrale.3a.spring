## Savinien Laeuffer, Projet back-end Spring boot

### 1. Description du projet

Ce projet est la partie back-end serveur d'une application web de partage de musiques. Ce projet vise à remplacer mon groupe messenger avec mes amis sur lequel on envoie des musiques peu connues chaque jour, par une application web avec des fonctionnalités supplémentaires qui répertorie tous les envois plus facilement.
Ici on se sert de Java et plus particulièrement du framework Spring boot ainsi que de PostgreSQL.

On a la possibilité d'ajouter des artistes qui possèdent des discographies (listes de musiques), les consulter, et les supprimer.
On a de plus la possibilité d'ajouter des musiques (titre, artiste, genre, url,)

### 2. Tests de l'API

Pour tester l'API il faut cloner le dossier, configurer le fichier `application.properties` afin de le lier à une base de données
PostgreSQL.
Ensuite il suffit de lancer le projet pour démarrer l'API

Avec Postman, on peut exécuter les requêtes test.
- `POST ./appsong/postArtist/`
- `POST ./appsong/postSong/`
- `GET ./appsong/getArtist/`
- `GET ./appsong/getSong/`
- `GET ./appsong/getAllArtists`
- `GET ./appsong/getAllSongs`
- `GET ./appsong/getSongById/{id}`
- `GET ./appsong/getArtistById/{id}`
- `DELETE ./appsong/removeArtist/{id}`
- `DELETE ./appsong/removeSong/{id}`