INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES('Redacted', 'Redacted', 'redacted.redacted@student.vinci.be', '$2b$10$vjhEQfESX5FGkVv9A05lrOj07Kf49Y/L280rN848AzLW7kWCnnRnu', true);
INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES('Redacted', 'Redacted', 'redacted.redacted@student.vinci.be', '$2b$10$5UQqBylUcjX6u2nqk1Ny9er4nxi38KTx11Pnn/nKcBR9vTPJRIVQe', true);

INSERT INTO categories(name) VALUES ('Java');
INSERT INTO categories(name) VALUES ('Web');
INSERT INTO categories(name) VALUES ('C');
INSERT INTO categories(name) VALUES ('Python');
INSERT INTO categories(name) VALUES ('Android');
INSERT INTO categories(name) VALUES ('Linux');
INSERT INTO categories(name) VALUES ('Windows');

-- Category: Java
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment faire une condition ?', 'Bonjour, je ne sais pas comment faire une condition en Java, pouvez-vous expliquer ?', false, 1, 1, true);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment faire une boucle for ?', 'Bonjour, je ne sais pas comment faire une boucle for en Java, ou placer les éléments, pouvez-vous expliquer ?', false, 1, 1, false);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment faire une boucle while', 'Bonjour, je ne sais pas comment faire une boucle while en Java, pouvez-vous expliquer ?, les conditions à mettre plus précisément.', false, 2, 1, 1);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment faire une boucle for mais avec un Integer', 'Bonjour, je ne sais pas comment faire une boucle for utilisant un Integer en Java, pouvez-vous expliquer ? Bande de pas beau', true, 2, 1, false);

INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('Lis la doc, pas compliquer quand meme!', false, 1, 2, false);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('Débrouille toi un peu, ca se trouve partout sur internet la réponse', false, 1, 2, false);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('if (condition) { ton code }', false, 1, 1, true);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('Join my discord server for nitro -> https://discord.gg/QurP66HMP3', true, 1, 1, false);

-- Category: Web
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment récupèrer la page sur le serveur web ?', 'Bonjour, je ne sais pas comment récupèrer la page codée sur mon navigateur.', false, 1, 2, true);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Comment créer une page web ?', 'Pouvez-vous me guider pour la réalisation de mon site ?', false, 1, 2, false);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Aide ?', 'Je cherhe qlq pour aider moi et mon ami à plein temps', false, 2, 2, false);
INSERT INTO questions(title, subject, is_reported, id_user, id_category, is_solved) VALUES ('Je suis perdu', 'Une erreur est apparue dans mon code mais je sais pas ou la trouver!!!', true, 2, 2, false);

INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('OUVREZ UN NAVIGATEUR ET TAPER LA QUESTION, 100 REPONSES EN UN COUP BORDEL', true, 5, 2, false);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('Tu te foutrais pas un peu du monde ?', false, 5, 2, false);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('Double clique dessus dans ton explorateur de fichiers', false, 5, 1, true);
INSERT INTO answers(question_answer, is_reported, id_question, id_user, is_correct) VALUES ('PTN MAIS TES SUPER CON MA PAROLE', true, 5, 1, false);
