# Java_rentmanager

## Description

Ce projet GIT contient le code source d'une application WEB de location de voitures développé en JAVA. Il permet de gérer les différents clients, les différents véhicules ainsi que les divers locations. Ce projet nous permet d'explorer la programmation orientée objet ainsi que le développement de site WEB en JAVA liées à une base de donnée SQL (Concept DAO, Service).


## Fonctionnalités

### Clients
- Listing des clients
- Ajout et suppression de clients
- Modification des informations du client
- Détails des clients et des réservations / véhicules associés

### Véhicules
- Listing des véhicules
- Ajout et suppression de véhicules
- Modification des informations du véhicules
- Détails des véhicules et des réservations associés

### Réservations
- Listing des réservations
- Ajout et suppression de réservations

## Contraintes

Certaines contraintes ont été ajouté afin de répondre aux besoins de l'application et donc d'avoir des données fiables:

- un client n'ayant pas 18ans ne peut pas être créé
- un client ayant une adresse mail déjà prise ne peut pas être créé
- le nom et le prénom d'un client doivent faire au moins 3 caractères
- une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur
- si un client ou un véhicule est supprimé, alors il faut supprimer les  réservations associées
- une voiture doit avoir un modèle et un constructeur, son nombre de place doit être compris entre 2 et 9

## Ajout

La base de donnée initiale a été légèrement modifié à travers le script "FillDatabase.java" afin d'ajouter l'attribut "modele" à la TABLE "vehicle". Le modele est donc présent dans le listing et les informations des véhicules.

## Contexte

L'application a été développé dans le cadre du cours "Langage JAVA" à l'EPF.
