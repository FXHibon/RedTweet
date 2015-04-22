RedTweet
=========

Introduction
---------

1. How To
---------

- Le fichier tp.war est prêt à être déployé sur un tomcat. L'appli tentera de se connecter à un serveur redis en local sur le port par défaut (6379).
- Pour modifier l'adresse de redis, `fr.epsi.tp.redtweet.dao.helper.DbHelper.java` est a modifidf pour rentrer les infos adéquates (IP, et mot de passe auth si nécessaire)
- Lancez un `bower install` à la racine du projet, puis un `mvn package` pour obtenir le war prêt à être déployé.


2. Technos utilisés
---------

Liste des principales techno utilisées:

* Server-side
    * Java 1.8, pour le langage
    * Tomcat 1.8, comme conteneur de servlet ("serveur d'application" light)
    * Spring, pour l'injection de dépendance et la mise en place d'une API REST
    * Maven, pour gérer les dépendances
    * Redis, comme serveur de base de données

* Client-side
    * angular, pour le framework js
    * angular-material pour le design
    * bower pour la gestion des dépendances
    * et quelques autres modules liés à angular
    
Application testé uniquement sous chrome dans sa dernière version, le serveur tournant sous un tomcat 8 avec un JVM 1.8

2. Modélisation [Redis](http://redis.io/)
---------

**HASH user:{userId}** => Informations sur un utilisateur, où *userId* est le nom d'utilisateur  
**ZSET timeLine:{userId}** => Ensemble de tweets ordonnés par date de création. Correspond à la "page d'acceuil" de l'utilisateur (ses tweets, ceux de ses following et ses retweets)  
**HASH tweet:{tweetId}** Informations d'un tweet (date de création, auteur, contenu ...)  
**SET user:{userId}:followers** Ensemble des followers (on sauvegarde juste l'identifiant des users correspondant) de {userId}  
**SET user:{userId}:following** Ensemble des following de {userId}  
**SET user:{userId}:tweets** Ensemble des tweets (uniquement l'id, comme pour les ensembles ci-dessus)  
**SET user:{userId}:retweets** Ensemble des retweets de {userId}  
**SET user:{userId}:favorites** Ensemble des tweets favoris de {userId}  

Lors qu'un utilisateur soulmt un tweet, on l'ajoute donc au ZSET , avec comme poids la date. Cela permet de retrouver rapidement tout les tweets qu'un utilisateur est censé voir.
De plus, on boucle sur la liste de ses followers pour l'ajouter à la timeLine de chaque utilisateur concerné. Ainsi, on propage le tweet pour toute les personnes suivant l'utilsiateur d'origine.

3. Divers
---------

Etant donné que c'est la première fois que je concevais une structure de donnée Redis, j'ai essayer différentes techniques. Il est évident que certains ne sont pas optimisés.

Par exemple, je me rend compte que je suis très souvent amené à boucler "à la main" pour faire certains traitements (propagation de tweet par exemple), en récupérant des identifiant en base à chaque itération pour aller chercher d'autres donéees etc ...
Je ne suis pas sûr que cela soit le meilleur choix, bien que pour une petite application, les performances de redis suivent sans aucun problèmes, même avec de mauvais choix de conception.
Redis fourni des fonctionalités assez intéressantes, comme les opérations ensemblistes par exemple. Je pense que j'aurais dû esaayer de plus/mieux les exploiter.

PS: lors du démarrage de l'application, des faux comptes sont créés pour tester l'appli.
Il y en a 10 leur nom d'utilisateurs sont "testx" avec x allant de 0 à 9. Les mots de passes sont les nom d'utilisateurs.