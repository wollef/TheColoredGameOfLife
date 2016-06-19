import java.util.Random;

public class JeuDeLaVie {

	public static void affichelagrille(int [][] grille) {

		 String resultat = "\n_________________________________________________\n";
		 
	 for (int row = 0; row < grille.length; row ++) {
		 resultat+= ""+row+" :";
       for (int col = 0; col < grille[row].length; col++) {
           if(grille [row] [col] ==0){
        	   resultat+=" ";
           } else{            
        	   resultat+="*";
           }
       }
	   resultat+="\n";

	 }
	 
     System.out.println(resultat);

	 }
	 
	 
	public static void main(String[] args) {
		
		int grille1 [][]= new int[50][200];
		int grille2 [][]= new int[50][200];
		
		// remplit la grille au hasard
		Random rnd = new Random();
		 for (int rowx = 0; rowx < grille1.length; rowx ++) {
	            for (int col = 0; col < grille1[rowx].length; col++) {
	                if (rnd.nextBoolean()) {
		                grille1 [rowx] [col] = 1;	                	
	                }else {
		                grille1 [rowx] [col] = 0;	                	
	                }
	            }
         }
	         
		 for(int fois=0;fois<10000;fois++) {
			
		     // affiche la grille 
			 affichelagrille(grille1);  

			 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	           // suspendu pendant 60 seconde (chiffre en millisecondes)

			 
	 		// calcul des vivantes mortes
			 for (int row = 1; row < grille1.length-1 ; row ++) {
	                // je comptre le nombre de vivantes autours
		            for (int col = 1; col < grille1[row].length-1; col++) {
						int nombrevivantes=
							grille1 [row-1] [col-1]+
							grille1 [row-1] [col]+ 
							grille1 [row-1] [col+1]+
							
							grille1 [row] [col-1]+
							grille1 [row] [col+1]+ 
							
							grille1 [row+1] [col-1]+
							grille1 [row+1] [col]+
							grille1 [row+1] [col+1];
		            
		            if(grille1 [row] [col] ==0){
		            	// si elle est morte
		            	if(nombrevivantes==3) {
		            		grille2[row][col]=1;
		            	}else {
		            		grille2 [row][col]=0;
		            	}
		            
		            } else{            
		            	// elle est vivante
		            	if( (nombrevivantes==2) ||  (nombrevivantes==3) ){
		            		grille2[row][col]=1;
		            	}else {
		            		grille2 [row][col]=0;
		            	}
		            }
				 }
			 }
		          
		     // affiche la grille 
			 int grillex [][] = grille1;
			 grille1 = grille2;
			 grille2 = grillex;
		 }
				 
		  
	 }

}
