Université d'Avignon et Pays du Vaucluse

Master Informatique - Ingénierie du Logiciel de la Société Numérique

SILVEIRA FEITOSA Tiago
M1 ILSEN CLASSIQUE

[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/Md4kR3LTkTA6tuHXGNDAk2/5Rx5oK48RkDDiUFP8RAnYZ/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/Md4kR3LTkTA6tuHXGNDAk2/5Rx5oK48RkDDiUFP8RAnYZ/tree/master)

[![codecov](https://codecov.io/gh/tiagofdev/ceri-m1-techniques-de-test/graph/badge.svg?token=NKE1XLFV7C)](https://codecov.io/gh/tiagofdev/ceri-m1-techniques-de-test)

# Projet Techniques de Test

Ce projet est une implémentation en Java d'une API Pokedex, conçue pour modéliser et récupérer des informations sur divers Pokémon. L'application inclut des fonctionnalités telles que la récupération de métadonnées de Pokémon, la création d'instances de Pokémon, et la gestion d'une collection Pokedex.

## Structure et Objectif du Projet

L'API Pokedex définit plusieurs interfaces, dont :
- **`IPokedex`** : Gère une liste de Pokémon, permettant leur récupération, tri et ajout.
- **`IPokemonFactory`** : Responsable de la création d'instances de Pokémon.
- **`IPokemonMetadataProvider`** : Fournit les métadonnées sur les Pokémon, comme les statistiques et les noms.

Ces interfaces permettent des implémentations extensibles qui peuvent être testées et utilisées dans divers contextes.

## Approche de Test

Ce projet utilise **JUnit 4.13.2** et **Mockito** pour les tests. Les tests visent à vérifier la fonctionnalité et le comportement de chaque interface et classe pour garantir leur exactitude et fiabilité. Les principales techniques de test incluent :
- **Tests Unitaires** : Chaque méthode d'interface et fonction de classe est testée isolément pour confirmer qu'elle se comporte comme prévu.
- **Mocking** : Mockito est utilisé pour créer des mocks de dépendances, permettant des tests isolés pour chaque composant. Par exemple, des mocks sont créés pour les dépendances telles que `IPokemonMetadataProvider` et `IPokemonFactory` afin de tester l'interface `IPokedex` indépendamment.
- **Vérification du Comportement** : La méthode `verify` de Mockito assure que certaines méthodes sont appelées comme prévu avec des arguments spécifiques.

## Outils et Technologies

### Java et Maven
- **Java** : Le projet utilise Java 21, exploitant les fonctionnalités modernes du langage.
- **Maven** : Utilisé pour la gestion des dépendances et l'automatisation du build.

### Intégration Continue avec CircleCI
- **CircleCI** : Un pipeline CircleCI est configuré pour l'intégration continue. Le pipeline défini dans `config.yml` inclut :
  - **Mise en Cache des Dépendances** : Des fichiers clés (comme `pom.xml`) sont utilisés pour créer une clé de cache, accélérant les builds.
  - **Tests et Vérification** : `mvn test` exécute les tests unitaires, tandis que `mvn verify` garantit que le projet est bien construit.
  - **Rapport de Couverture** : La couverture des tests est envoyée à Codecov, améliorant la visibilité de la qualité et de la couverture du code.

### Couverture de Code avec Codecov
- **Codecov** : Le projet intègre Codecov pour surveiller la couverture de code. Les rapports de couverture sont téléchargés sur Codecov, fournissant des informations sur la part de code testée et identifiant les zones non testées.

### Configuration Supplémentaire
- **Docker** : Le pipeline s'exécute dans des environnements Docker, permettant des builds cohérents et isolés. Le projet utilise l'image `cimg/openjdk:21.0` pour l'environnement de build Java.

---


************************************************************************************************************************************************************************

# UCE Génie Logiciel Avancé : Techniques de tests

## Introduction

Vous allez à travers ces projet mettre en application une partie des aspects évoqués en cours vis à vis des techniques de tests.  
Pour cela nous allons réaliser un projet logiciel de petite taille, en suivant la roadmap suivante : 
- Setup du projet
- Mise en place des outils d’intégration continue
- Écriture des tests unitaires
- Écriture des mocks, et validation des tests
- Développement dirigé par les tests
- Documentation et conventions de style
- Test d'une implémentation donnée

Durant cette série de TPs, le gestionnaire de version Git sera utilisé à foison, à travers la plateforme GitHub. Si vous n’êtes pas à l’aise avec cet outil[^1], [voici](http://rogerdudler.github.io/git-guide/) un petit guide à garder sous la main.

## Sujets

L'ensemble des sujets de TPs peut être trouvé dans le dossier `TPs`.

Le dossier `src` contient la définition de l'ensemble des interfaces qui seront l'objet de vos travaux.

## Rendus

Le rendu des TPs se fait au rythme suivant :

- TP1 : 2ème séance
- TP2 : 2ème séance
- TP3 : 3ème séance
- TP4 : 5ème séance
- TP5 : dernière séance
- TP6 : dernière séance

Pour chaque rendu vous devez créer un tag à partir du commit qui correspond à la complétion du TP.  
Si vous ne spécifiez pas de tag, le dernier commit à la date-heure de la fin de séance sera celui considéré.

[^1]: Si vous n’êtes vraiment pas à l’aise avec cet outil nous vous conseillons quand même vivement de vous y mettre.
