CREATE TABLE BRASSERIE (
                           idBrasserie INT AUTO_INCREMENT PRIMARY KEY,
                           nom VARCHAR(50) NOT NULL,
                           adresse VARCHAR(250),
                           coordGPS VARCHAR(50),
                           dtOuverture DATE
);
CREATE TABLE BIERE (
                       idBiere INT AUTO_INCREMENT PRIMARY KEY,
                       nom VARCHAR(50) NOT NULL,
                       tyBiere VARCHAR(50),
                       description VARCHAR(250),
                       dgAlcool INT,
                       note INT,
                       idbrasserie INT,
                       FOREIGN KEY (idbrasserie) REFERENCES brasserie(idbrasserie)
);