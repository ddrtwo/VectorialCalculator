

public class Vecteur extends Point {
    //*** CONSTRUCTEURS
    public Vecteur() {
        this.setx(0, (byte) 0);
        this.sety(0, (byte) 0);
        this.setz(0, (byte) 0);
    }

    public Vecteur(Point A, Point B) {
        this.setx((B.getx((byte) 0) - A.getx((byte) 0)), (byte) 0);
        this.sety((B.gety((byte) 0) - A.gety((byte) 0)), (byte) 0);
        this.setz((B.getz((byte) 0) - A.getz((byte) 0)), (byte) 0);
        this.setName(A.getName() + "->" + B.getName());
    }

    public Vecteur(double abs, double ord, double elv, byte b) {
        this.setx(abs, b);
        this.sety(ord, b);
        this.setz(elv, b);
    }

    public Vecteur(double abs, double ord, byte b) {
        this.setx(abs, b);
        this.sety(ord, b);
        this.setz(0.0, b);
    }
    //*** FIN DES CONSTRUCTIONS


    @Override
    public String toString()    // redéfinition de la fonction ToString pour afficher p exple le vecteur
    {
        String nom = "vec-" + super.getName();
        return nom;
    }

    public Double norme() {
        return this.getx((byte) 2);
    }

    public Double[] direction() {
        Double[] tableau = new Double[2];
        tableau[0] = this.cm.getY((byte) 2);
        tableau[1] = this.cm.getZ((byte) 2);
        return tableau;
    }
}
