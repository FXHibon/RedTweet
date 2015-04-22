RedTweet
=========

Introduction
---------

1. How To
---------

- Le fichier tp.war est pr�t � �tre d�ploy� sur un tomcat. L'appli tentera de se connecter � un serveur redis en local sur le port par d�faut (6379).
- Pour modifier l'adresse de redis, `fr.epsi.tp.redtweet.dao.helper.DbHelper.java` est a modifidf pour rentrer les infos ad�quates (IP, et mot de passe auth si n�cessaire)
- Lancez un `bower install` � la racine du projet, puis un `mvn package` pour obtenir le war pr�t � �tre d�ploy�.


2. Technos utilis�s
---------

Liste des principales techno utilis�es:

* Server-side
    * Java 1.8, pour le langage
    * Tomcat 1.8, comme conteneur de servlet ("serveur d'application" light)
    * Spring, pour l'injection de d�pendance et la mise en place d'une API REST
    * Maven, pour g�rer les d�pendances
    * Redis, comme serveur de base de donn�es

* Client-side
    * angular, pour le framework js
    * angular-material pour le design
    * bower pour la gestion des d�pendances
    * et quelques autres modules li�s � angular
    
Application test� uniquement sous chrome dans sa derni�re version, le serveur tournant sous un tomcat 8 avec un JVM 1.8

2. Mod�lisation [Redis](http://redis.io/)
---------

**HASH user:{userId}** => Informations sur un utilisateur, o� *userId* est le nom d'utilisateur  
**ZSET timeLine:{userId}** => Ensemble de tweets ordonn�s par date de cr�ation. Correspond � la "page d'acceuil" de l'utilisateur (ses tweets, ceux de ses following et ses retweets)  
**HASH tweet:{tweetId}** Informations d'un tweet (date de cr�ation, auteur, contenu ...)  
**SET user:{userId}:followers** Ensemble des followers (on sauvegarde juste l'identifiant des users correspondant) de {userId}  
**SET user:{userId}:following** Ensemble des following de {userId}  
**SET user:{userId}:tweets** Ensemble des tweets (uniquement l'id, comme pour les ensembles ci-dessus)  
**SET user:{userId}:retweets** Ensemble des retweets de {userId}  
**SET user:{userId}:favorites** Ensemble des tweets favoris de {userId}  

Lors qu'un utilisateur soulmt un tweet, on l'ajoute donc au ZSET , avec comme poids la date. Cela permet de retrouver rapidement tout les tweets qu'un utilisateur est cens� voir.
De plus, on boucle sur la liste de ses followers pour l'ajouter � la timeLine de chaque utilisateur concern�. Ainsi, on propage le tweet pour toute les personnes suivant l'utilsiateur d'origine.

3. Divers
---------

Etant donn� que c'est la premi�re fois que je concevais une structure de donn�e Redis, j'ai essayer diff�rentes techniques. Il est �vident que certains ne sont pas optimis�s.

Par exemple, je me rend compte que je suis tr�s souvent amen� � boucler "� la main" pour faire certains traitements (propagation de tweet par exemple), en r�cup�rant des identifiant en base � chaque it�ration pour aller chercher d'autres don�ees etc ...
Je ne suis pas s�r que cela soit le meilleur choix, bien que pour une petite application, les performances de redis suivent sans aucun probl�mes, m�me avec de mauvais choix de conception.
Redis fourni des fonctionalit�s assez int�ressantes, comme les op�rations ensemblistes par exemple. Je pense que j'aurais d� esaayer de plus/mieux les exploiter.

PS: lors du d�marrage de l'application, des faux comptes sont cr��s pour tester l'appli.
Il y en a 10 leur nom d'utilisateurs sont "testx" avec x allant de 0 � 9. Les mots de passes sont les nom d'utilisateurs.