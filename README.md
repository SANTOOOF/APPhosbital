# App-Hospital

## À propos

App-Hospital est une application web développée avec le framework Java Spring Boot. Elle a pour objectif principal la gestion des dossiers de patients au sein d'une structure hospitalière ou médicale. L'application fournit une interface utilisateur basée sur le web, permettant aux utilisateurs autorisés de consulter, rechercher, ajouter, modifier et supprimer des informations relatives aux patients. Elle intègre également un système d'authentification et d'autorisation basé sur les rôles pour sécuriser l'accès aux différentes fonctionnalités. Le projet utilise une pile technologique moderne comprenant Spring Boot pour le backend, Spring Data JPA pour l'interaction avec la base de données, Spring Security pour la gestion de la sécurité, et Thymeleaf pour le rendu des vues côté serveur, le tout stylisé avec Bootstrap.


## Fonctionnalités

L'application App-Hospital offre plusieurs fonctionnalités clés pour la gestion des patients :

*   **Gestion des Patients :** Le cœur de l'application réside dans sa capacité à gérer les informations des patients. Les utilisateurs disposant des droits appropriés (administrateurs) peuvent ajouter de nouveaux patients via un formulaire dédié, modifier les informations des patients existants, et supprimer des dossiers de patients. Les informations gérées incluent le nom, la date de naissance, un indicateur de maladie (booléen), et un score numérique.
*   **Consultation et Recherche :** Tous les utilisateurs authentifiés peuvent consulter la liste des patients enregistrés. Cette liste est paginée pour une meilleure navigation et inclut une fonctionnalité de recherche par nom, permettant de filtrer rapidement les résultats.
*   **Authentification et Sécurité :** L'accès à l'application est sécurisé par un système d'authentification basé sur Spring Security. Les utilisateurs doivent se connecter avec un nom d'utilisateur et un mot de passe pour accéder aux fonctionnalités. L'application utilise le cryptage BCrypt pour stocker les mots de passe de manière sécurisée.
*   **Gestion des Rôles et Autorisations :** L'application implémente une gestion des accès basée sur les rôles (RBAC). Deux rôles principaux sont définis : USER et ADMIN. Les utilisateurs standards (USER) ont un accès limité à la consultation et à la recherche de patients, tandis que les administrateurs (ADMIN) disposent de droits étendus pour effectuer toutes les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) sur les patients. L'accès aux fonctionnalités spécifiques est contrôlé via des annotations `@PreAuthorize`.
*   **Interface Utilisateur Web :** L'interface est construite avec Thymeleaf et Bootstrap, offrant une expérience utilisateur réactive et conviviale. Elle comprend une barre de navigation, des formulaires de saisie avec validation des données, des tableaux pour afficher les listes de patients, et des mécanismes de confirmation pour les actions sensibles comme la suppression.


## Prérequis

Avant de pouvoir compiler et exécuter l'application App-Hospital, assurez-vous que les éléments suivants sont installés sur votre système :

*   **Java Development Kit (JDK) :** L'application est développée avec Java 17. Vous devez donc avoir une version compatible du JDK (version 17 ou supérieure) installée.
*   **Maven :** Le projet utilise Maven comme outil de gestion de dépendances et de build. Assurez-vous que Maven est installé et configuré correctement dans votre environnement.
*   **Base de données :** L'application est configurée pour utiliser une base de données MySQL par défaut (voir le fichier `application.properties`). Vous devez avoir un serveur MySQL en cours d'exécution et accessible. Les informations de connexion (URL, nom d'utilisateur, mot de passe) doivent être ajustées dans le fichier `application.properties` si nécessaire. Bien que H2 soit également listé comme dépendance (probablement pour les tests ou le développement initial), la configuration par défaut pointe vers MySQL.


## Installation

Pour installer et lancer l'application App-Hospital sur votre machine locale, suivez les étapes ci-dessous :

1.  **Cloner le dépôt (ou extraire l'archive) :** Si le projet est sur un dépôt Git, clonez-le. Sinon, assurez-vous d'avoir extrait l'archive `app-hospital.zip` dans un répertoire de votre choix.
2.  **Configurer la base de données :** Ouvrez le fichier `src/main/resources/application.properties`. Modifiez les propriétés `spring.datasource.url`, `spring.datasource.username`, et `spring.datasource.password` pour correspondre à la configuration de votre serveur MySQL local. Assurez-vous que la base de données spécifiée (`app-hospital-db` par défaut) existe ou que l'option `createDatabaseIfNotExist=true` est présente dans l'URL JDBC pour permettre sa création automatique.
3.  **Compiler le projet :** Ouvrez un terminal ou une invite de commande, naviguez jusqu'au répertoire racine du projet (le dossier contenant le fichier `pom.xml`), et exécutez la commande Maven suivante pour compiler le projet et télécharger les dépendances :
    ```bash
    mvn clean install
    ```
4.  **Lancer l'application :** Une fois la compilation réussie, vous pouvez lancer l'application Spring Boot en utilisant Maven :
    ```bash
    mvn spring-boot:run
    ```
    Alternativement, vous pouvez exécuter le fichier JAR généré dans le répertoire `target` :
    ```bash
    java -jar target/app-hospital-0.0.1-SNAPSHOT.jar
    ```
5.  **Accéder à l'application :** Après le démarrage, l'application sera accessible via votre navigateur web à l'adresse `http://localhost:8086` (le port 8086 est défini dans `application.properties`).


## Utilisation

Une fois l'application lancée, vous pouvez y accéder via votre navigateur web.

1.  **Page de Connexion :** L'accès initial se fait via la page de connexion (`/login`). Vous devrez fournir un nom d'utilisateur et un mot de passe valides. Des utilisateurs et rôles peuvent être initialisés au démarrage (voir les sections commentées dans `AppHospitalApplication.java` ou la configuration de `SecurityConfig` et `UserDetailServiceImpl`). Par exemple, si vous avez décommenté et exécuté le `CommandLineRunner` dans `AppHospitalApplication` qui utilise `AccountService`, vous pourriez avoir des utilisateurs comme `user1` ou `admin` avec le mot de passe `1234`.
2.  **Tableau de Bord Principal :** Après une connexion réussie, les utilisateurs sont redirigés vers la page d'index (`/user/index`) qui affiche la liste paginée des patients. Cette page permet également de rechercher des patients par nom.
3.  **Gestion des Patients (Administrateurs) :**
    *   **Ajouter un patient :** Les administrateurs peuvent accéder au formulaire d'ajout de patient via le menu déroulant "Patients" > "Nouveau" (`/admin/formPatients`). Remplissez les détails du patient et cliquez sur "Save".
    *   **Modifier un patient :** Dans la liste des patients, cliquez sur le bouton "Edit" (icône crayon) à côté du patient concerné (`/admin/editPatient`). Modifiez les informations dans le formulaire et cliquez sur "Save".
    *   **Supprimer un patient :** Dans la liste des patients, cliquez sur le bouton "Supprimer" (icône poubelle) à côté du patient concerné (`/admin/delete`). Une confirmation sera demandée avant la suppression.
4.  **Déconnexion :** Pour vous déconnecter, utilisez le menu déroulant portant votre nom d'utilisateur en haut à droite et cliquez sur "Logout".


## Sécurité

La sécurité de l'application App-Hospital est gérée par Spring Security. Voici les aspects clés de la configuration de sécurité :

*   **Authentification :** L'application utilise une authentification basée sur un formulaire de connexion (`/login`). Les informations d'identification des utilisateurs (nom d'utilisateur, mot de passe haché, rôles) sont gérées via une implémentation personnalisée `UserDetailServiceImpl` qui interagit probablement avec la base de données (via `AppUserRepository` et `AppRoleRepository`, bien que l'implémentation exacte de `UserDetailServiceImpl` n'ait pas été fournie dans l'archive initiale, c'est l'approche standard avec les entités `AppUser` et `AppRole` présentes). Le stockage des mots de passe utilise l'encodeur BCrypt.
*   **Autorisation :** Le contrôle d'accès est basé sur les rôles. Les annotations `@PreAuthorize` sont utilisées au niveau des méthodes des contrôleurs (par exemple, dans `PatientController`) pour restreindre l'accès à certaines fonctionnalités aux utilisateurs ayant des rôles spécifiques (principalement `ROLE_ADMIN` pour les opérations de modification et de suppression).
*   **Configuration :** La configuration principale de la sécurité se trouve dans la classe `SecurityConfig`. Elle définit la chaîne de filtres de sécurité, configure la page de connexion personnalisée, active la protection CSRF (par défaut avec Spring Security), gère les exceptions d'accès refusé (redirigeant vers `/notAuthorized`), et configure le service de détails utilisateur (`UserDetailServiceImpl`). La configuration permet également l'accès public aux ressources statiques (webjars) et à la console H2 (si activée).
*   **Initialisation des utilisateurs/rôles :** Le code contient des sections commentées (`CommandLineRunner` beans dans `AppHospitalApplication`) qui suggèrent des mécanismes pour initialiser des utilisateurs et des rôles au démarrage, soit via `JdbcUserDetailsManager`, soit via un service personnalisé `AccountService`. Ces sections devraient être décommentées et adaptées si une initialisation automatique est souhaitée.


## Base de données

L'application utilise Spring Data JPA pour interagir avec la base de données relationnelle. Voici les détails concernant la configuration et le schéma :

*   **Système de Gestion de Base de Données (SGBD) :** La configuration par défaut dans `application.properties` est prévue pour **MySQL**. Les paramètres de connexion (URL, utilisateur, mot de passe) doivent être ajustés pour correspondre à votre environnement MySQL local. La dépendance pour le connecteur MySQL (`mysql-connector-j`) est incluse dans le `pom.xml`.
*   **Base de données en mémoire (H2) :** La dépendance pour H2 est également présente dans le `pom.xml`, et la configuration pour utiliser H2 est commentée dans `application.properties`. H2 pourrait être utilisée pour le développement ou les tests en décommentant les lignes correspondantes (`spring.datasource.url=jdbc:h2:mem:patients-db` et `spring.h2.console.enabled=true`).
*   **Gestion du Schéma :** La propriété `spring.jpa.hibernate.ddl-auto` est définie sur `update` dans `application.properties`. Cela signifie qu'Hibernate tentera de mettre à jour automatiquement le schéma de la base de données en fonction des entités JPA définies (`Patient`, `AppUser`, `AppRole`) au démarrage de l'application. Un fichier `schema.sql` est également présent dans les ressources, contenant des instructions SQL pour créer les tables `users` et `authorities` utilisées par Spring Security (probablement pour le `JdbcUserDetailsManager`, bien que l'implémentation active utilise `UserDetailServiceImpl`). La propriété `spring.sql.init.mode=always` assure que ce script SQL est exécuté au démarrage.
*   **Entités JPA :** Les principales entités sont `Patient` (représentant les informations du patient), `AppUser` (représentant un utilisateur de l'application), et `AppRole` (représentant un rôle). Ces entités sont mappées aux tables correspondantes de la base de données.


## Licence

Aucune licence n'est spécifiée pour ce projet dans les fichiers fournis. Il est recommandé d'ajouter un fichier `LICENSE` à la racine du projet pour clarifier les conditions d'utilisation et de distribution du code.

## Contribution

Les directives de contribution ne sont pas définies pour ce projet. Si vous souhaitez ouvrir ce projet aux contributions externes, veuillez ajouter un fichier `CONTRIBUTING.md` détaillant le processus pour proposer des modifications ou signaler des problèmes.
