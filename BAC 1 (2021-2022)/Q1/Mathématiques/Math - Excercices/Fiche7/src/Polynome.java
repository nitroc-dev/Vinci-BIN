public class Polynome {
	
   private static final int MAX_ETAPE=1000;
   private double[] coefficients; // Coefficients du polyn�me
   private int degre; // Degr� du polyn�me
	
	// Cr�e le polyn�me x^degre
	// Lance une IllegalArgumentException si degre est n�gatif
   public Polynome(int degre) throws IllegalArgumentException{
      if (degre < 0) throw new IllegalArgumentException("Le degr� du polyn�me doit �tre positif");
      this.degre = degre;
      this.coefficients = new double[degre+1];
      for (int i = 0; i < degre ; i++){
         this.coefficients[i] = 0;
      }
      this.coefficients[this.degre] = 1;
   }
	
	// Cr�e le polyn�me nul
   public Polynome(){
      this.degre = 0;
      this.coefficients = new double[1];
      this.coefficients[0] = 0;
   }
	
	// Assigne la valeur coefficient au coefficient de degr� degre du polynome
	// Lance une IllegalArgumentException si degre est n�gatif ou sup�rieur au degr� du polyn�me
	// Lance une IllegalArgumentException si on tente de changer le degr� du polyn�me
   public void setCoefficient(int degre,double coefficient) throws IllegalArgumentException{
      if (degre < 0 || degre > this.degre) throw new IllegalArgumentException("degr� invalide");
      if (degre == this.degre && coefficient ==0 && this.degre != 0){
         throw new IllegalArgumentException("Le coefficient de degr� dominant ne peut pas �tre nul");
      }
      this.coefficients[degre] = coefficient;
   }
	
	// Renvoie la valeur du polyn�me �valu� en x
   public double evalueEn(double x){
      double result = this.coefficients[this.degre];
      for (int i = this.degre-1; i >= 0 ; i--){
         result = result * x + this.coefficients[i];
      }
      return result;
   }
	
	// Renvoie la valeur du degr� dominant du polyn�me
   public int getDegre() {
      return degre;
   }

	// Renvoie le polyn�me d�riv� de this
   public Polynome polynomeDerive(){
      try {
         Polynome p = new Polynome(this.degre-1);
         for (int i = 0; i < this.degre ; i++){
            p.coefficients[i] = (i+1) * this.coefficients[i+1];
         }
         return p;
      } catch (IllegalArgumentException e) {
         return new Polynome();
      }	
   }
	// renvoie une description du polyn�me courant, sous forme de chaine de caract�res
   public String toString(){
      if (degre == 0) 
         return "" + this.coefficients[0];
      String result = "" + this.coefficients[degre]+" x^" + degre;
      for (int i = degre-1  ; i > 0 ; i--){
         if (this.coefficients[i] < 0) {
            result += "-" + Math.abs(this.coefficients[i]) + " x^" + i;				
         }
         else {
            if (this.coefficients[i] > 0){
               result += " + " + this.coefficients[i] + " x^" + i;
            }
         }
      }
      if (this.coefficients[0] < 0) {
         result += " - "+ Math.abs(this.coefficients[0]) ; 				
      } else {
         if (this.coefficients[0] > 0){
            result += " + " + this.coefficients[0] ;
         }
      }
      
      return result ;
	  
   }
   
	// Renvoie une approximation � minimum d d�cimales exactes de la racine contenue dans
	// l'intervalle [a ; b] ou [b ; a], obtenue par la m�thode de la bissection
	// Lance une NumeriqueException en cas de probl�me
   public double racineParBissection(double a, double b, int d) throws NumeriqueException{
      if (this.evalueEn(a)==0) return a ;  // si les racines sont en a et en b !
      if (this.evalueEn(b)==0) return b ;
      if((this.evalueEn(a)*this.evalueEn(b)) >= 0) throw new NumeriqueException("m�thode inefficace ! "); // m�thode de bolzano
      if (a>b) {   // inverser a et b si b est plus grand que a
         double inverseur = a;
         a=b;
         b=inverseur;
      }
      double milieu = (a+b)/2 ;
      double precision = (1/2.0)*Math.pow(10,-d);
      for (int i = 0; i<MAX_ETAPE; i++) {
         // a et b modifiables
         if (this.evalueEn(milieu)==0) return milieu ;
         if(((b-a)/2) < precision) {
            return milieu;
         }
         if((evalueEn(a)*evalueEn(milieu) <0)) { //sont de signe contraire
            b = milieu;
         } else {
            a = milieu;
         }
         milieu = (a+b)/2;
      }
      throw new NumeriqueException("m�thode inefficace ! ");
   }

	// Renvoie une approximation � minimum d d�cimales exactes de la racine contenue dans
	// l'intervalle [a ; b] ou [b ; a], obtenue par la m�thode de Newton
	// Lance une NumeriqueException en cas de probl�me
   public double racineParNewton(double a,double b, int d) throws NumeriqueException{
      if (this.evalueEn(a)==0) return a ;  // si les racines sont en a et en b !
      if (this.evalueEn(b)==0) return b ;
      if((this.evalueEn(a)*this.evalueEn(b)) >= 0) throw new NumeriqueException("m�thode inefficace ! "); // m�thode de bolzano
      double x = a;   // ou b  ???
      double pointFixe = b;
      double precision = (1/2.0)*Math.pow(10,-d);
      Polynome derivee = this.polynomeDerive();
      for (int i = 0; i<MAX_ETAPE; i++) {
         if((Math.abs(x)/derivee.evalueEn(pointFixe)) < precision) {
            return x;
         }

         x = x - (evalueEn(x)/derivee.evalueEn(x));

      }
      throw new NumeriqueException("m�thode inefficace ! ");
   }
}
