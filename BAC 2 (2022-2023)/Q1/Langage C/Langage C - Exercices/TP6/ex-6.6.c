#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>

int choixOperation ();
int** creerImage (int largeur, int hauteur);
void initPixels (int** image, int l, int h, int p, bool aleatoire);
void afficherImage (int** image, int l, int h);
int** redimensionner (int** image, int l, int h, int newL, int newH);
int* histogramme (int** image, int l, int h,  int p);
void afficherHistogramme (int* histo, int p);
void libererImage (int*** image, int l, int h);
            

/// PROGRAMME PRINCIPAL

int main() {
    int choix, n, m, nBits, maxVal, newN, newM;
    bool continuer = true; 
    int** image = NULL;
    int* histo;
    
    while (continuer) {
        choix = choixOperation();
        switch (choix) {
        case 1: case 2:
            if (image != NULL) {
                printf("Erreur: l'image courante doit être supprimée avant d'en créer une nouvelle\n");
                continue;
            }
            printf("Entrez les dimenions de l'image, au format (n,m): ");
            scanf("\n(%d,%d)", &n, &m);  // voir commentaire en fin de programme
            printf("Entrez la profondeur de bits de l'image: ");
            scanf("%d", &nBits);            
            printf("Création d'une image (%d,%d) de profondeur de bits %d " \
            "avec des valeurs %s pour les pixels\n", 
            n, m, nBits, (choix==1) ? "aléatoires" : "prédéfinies");
            
            image = creerImage(n, m);
            if (image == NULL) {
                perror("Erreur creerImage: malloc impossible\n");
                exit(1);
            }
            initPixels(image, n, m, nBits, choix==1);
            break;
            
        case 3:
            afficherImage(image, n, m);
            break;
            
        case 4:
            if (image == NULL) {
                printf("Aucune image courante\n");
                continue;
            }
            printf("Entrez les nouvelles dimensions de l'image, au format (n,m): ");
            scanf("\n(%d,%d)", &newN, &newM);
            if ((newN >= n && newM <= m) || (newN <= n && newM >= m)) {
                printf("Dimensions non valides\n");
                continue;
            }
            image = redimensionner(image, n, m, newN, newM);
            if (image == NULL) {
                perror("Erreur redimensionner: malloc impossible\n");
                exit(1);                
            }
            n = newN;
            m = newM;
            break;
            
        case 5:
            if (image == NULL) {
                printf("Aucune image courante\n");
                continue;
            }
            
            int* histo = histogramme(image, n, m, nBits);
			if (histo == NULL) {
                perror("Erreur histogramme: malloc impossible\n");
                exit(1);
            }
			afficherHistogramme(histo, nBits);
            printf("Nombre de pixels dans l'image: %d\n", n*m);
            
            free(histo);
            break;
            
        case 7:
            printf("Sortie du programme\n");
            continuer = false;
            // pas de break!
            
        case 6: 
            if (image == NULL) {
                printf("Aucune image à libérer\n");
				continue;
            } 
                
            libererImage(&image, n, m);
            break;
            
        default:
            printf("Erreur: opération non définie\n");
        }
    }
}


/// FONCTIONS

/**
 * Choix d'une opération par l'utilisateur
 * POST: affiche le menu du programme
 * RES: renvoie l'opération choisie par l'utilisateur
 */
int choixOperation () {
    
    do {
        printf("\nMENU:\n" \
        "\t1) Création d’image avec valeurs aléatoires\n" \
        "\t2) Création d’une image avec valeurs prédéfinies\n" \
        "\t3) Affichage de l’image\n" \
        "\t4) Changement de la taille de l’image\n" \
        "\t5) Affichage de l’histogramme de l’image\n" \
        "\t6) Suppression de l’image\n" \
        "\t7) Quitter le programme\n");
        printf("Entrez votre choix: ");
        scanf("%d", &choix);
    } while (choix<1 || choix>7);
    
    return choix;    
}

/**
 * Création d'une image
 * PRE: largeur, hauteur: les dimensions de l'image à créer
 * RES: renvoie une image de largeur X hauteur pixels si la création a réussi;
 *      sinon renvoie NULL 
 */
int** creerImage (int largeur, int hauteur) {
    
    int** image = malloc(n * sizeof(int*));
    if (image == NULL) 
        return NULL;

    for (int i=0; i<n; i++) {
        image[i] = (int*) malloc(m * sizeof(int));
        if (image[i] == NULL)
            return NULL;
    }
    
    return image;
}

/**
 * Initialisation des pixels d'une image
 * PRE: image: une image de l X h pixels
 *      p: la profondeur de bits de l'image
 *      aleatoire: indique si l'image doit être initialisée avec des valeurs aléatoires 
 *                 ou prédéfinies
 * POST: les pixels de l'image ont été initialisés à des valeurs aléatoires si 
 *       aleatoire vaut vrai ; à des valeurs prédéfinies sinon 
 *       (i.e. (indice de ligne + 1) % 2^p)
 */
void initPixels (int** image, int l, int h, int p, bool aleatoire) {
    
    srand((unsigned) time(NULL));  // voir explications dans solution de TP2 - exo 4
    int maxVal = pow(2,p);

    for (int i=0; i<l; i++)
        for (int j=0; j<h; j++)
            image[i][j] = aleatoire ? rand() % maxVal : (i+1) % maxVal;
}

/**
 * Affichage d'une image
 * PRE: image: une image de l X h pixels
 * POST: affiche l'image (i.e. la valeur des pixels) ou "image vide" si image vaut NULL
 */
void afficherImage (int** image, int l, int h) {
    if (image == NULL) {
        printf("Image vide\n");
        continue;
    }
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            printf("%5d", image[i][j]);
        }
        printf("\n");
    }
}

/**
 * Redimensionnement d'une image
 * PRE: image: une image de l X h pixels
 *      newL, newH: les nouvelles dimensions de l'image telles que newL>l et newH>h ou newL<l et newH<h
 * RES: renvoie l'image redimensionnée (à partir du coin supérieur gauche de l'image) 
 *      de telle sorte que, si la taille de la nouvelle image est
 *      - plus petite: l'image sera coupée (cropped) aux dimensions newL X newH
 *      - plus grande: l'image sera agrandie aux dimensions newL X newH et
 *        les nouveaux pixels auront la couleur noire (càd la valeur 0)
 */
int** redimensionner (int** image, int l, int h, int newL, int newH) {
    if (newL < l) {
        // decoupage (crop)
        for (int i=newL; i<l; i++) {
            free(image[i]);
        }
        image = realloc(image, newL * sizeof(int*));
        if (image == NULL) {
            perror("Erreur: malloc impossible\n");
            return NULL;
        }
        for (int i=0; i<newL; i++) {
            image[i] = realloc(image[i], newH * sizeof(int));
            if (image[i] == NULL) {
                perror("Erreur: malloc impossible\n");
                return NULL;   
            }
        }
    } else {
        // agrandissement
        image = (int**) realloc(image, newL * sizeof(int*));
        if (image == NULL) {
            perror("Erreur: malloc impossible\n");
            return NULL;
        }
        for (int i=0; i<l; i++) {
            image[i] = realloc(image[i], newH * sizeof(int));
            if (image[i] == NULL) {
                perror("Erreur: malloc impossible\n");
                return NULL;   
            }
            for (int j=h; j<newH; j++) {
                image[i][j] = 0;
            }
        }
        for (int i=l; i<newL; i++) {
            image[i] = (int*) calloc(newH, sizeof(int));  // ou malloc avec boucle d'initialisation des pixels à 0
            if (image[i] == NULL) {
                perror("Erreur: malloc impossible\n");
                return NULL;
            }
        }
    }
    return image;
}

/**
 * Histogramme d'une image
 * PRE: image: une image de l X h pixels avec une profondeur de bits p
 * RES: renvoie l'histogramme de l'image ; NULL en cas d'erreur
 */
int* histogramme (int** image, int l, int h,  int p) {
	int maxVal = pow(2,p);
    int* histo = calloc(maxVal, sizeof(int));  // ou malloc avec boucle d'initialisation des compteurs à 0
    if (histo == NULL)
        return NULL;
    
    for (int i=0; i<l; i++)
		for (int j=0; j<h; j++)
			histo[image[i][j]]++;
		
	return histo;
}

/**
 * Affichage d'un histrogramme
 * PRE: histo: un histogramme de dimension 2^p (où p représente la pronfondeur de bits de l'image)
 * POST: affiche l'histogramme 
 */
void afficherHistogramme (int* histo, int p) {
	printf("Histogramme de l'image:\n");
	int maxVal = pow(2,p);
    for (int* ptr=histo; ptr-histo<maxVal; ptr++) {
        if (*ptr != 0) {
            printf("\t#pixels de valeur %d: %d\n", (int)(ptr-histo), *ptr);
        }
    }
}

/**
 * Libération de la mémoire d'une image
 * PRE: image: une image de l X h pixels
 * POST: libère la mémoire de l'image et met *image à NULL
 */
void libererImage (int*** image, int l, int h) {
	
	for (int i=0; i<n; i++)
        free((*image)[i]);
    free(*image);
    *image = NULL;
}
