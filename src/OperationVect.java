

public class OperationVect {
    public Double norme(Vecteur a) {
        return a.norme();
    }

    public Double produitScal(Vecteur a, Vecteur b) {
        Double ans;
        ans = a.getx((byte) 0) * b.getx((byte) 0) + a.gety((byte) 0) * b.gety((byte) 0) + a.getz((byte) 0) * b.getz((byte) 0);
        return ans;
    }

    public Vecteur produitVect(Vecteur a, Vecteur b) {
        Vecteur c = new Vecteur();
        c.setx((a.gety((byte) 0) * b.getz((byte) 0) - b.gety((byte) 0) * a.getz((byte) 0)), (byte) 0);
        c.sety((b.getx((byte) 0) * a.getz((byte) 0) - b.getz((byte) 0) * a.getx((byte) 0)), (byte) 0);
        c.setz((a.getx((byte) 0) * b.gety((byte) 0) - b.getx((byte) 0) * a.gety((byte) 0)), (byte) 0);
        return c;
    }

    public Vecteur oppose(Vecteur a) {
        Vecteur x = new Vecteur();
        x.setx((0 - a.getx((byte) 0)), (byte) 0);
        x.sety((0 - a.gety((byte) 0)), (byte) 0);
        x.setz((0 - a.getz((byte) 0)), (byte) 0);
        return x;
    }

    public Vecteur somme(Vecteur a, Vecteur b) {
        Vecteur x = new Vecteur();
        x.setx((a.getx((byte) 0) + b.getx((byte) 0)), (byte) 0);
        x.sety((a.gety((byte) 0) + b.gety((byte) 0)), (byte) 0);
        x.setz((a.getz((byte) 0) + b.getz((byte) 0)), (byte) 0);
        return x;
    }

    public Vecteur soustract(Vecteur a, Vecteur b) {
        return somme(a, oppose(b));
    }

    public Vecteur dilatation(double k, Vecteur u) {
        Vecteur ans = new Vecteur(u.getx((byte) 0) * k, u.gety((byte) 0) * k, u.getz((byte) 0) * k, (byte) 0);
        return ans;
    }

    public boolean areParallel(Vecteur u, Vecteur v) {
        boolean ans = false;
        double k = u.getx((byte) 0) / v.getx((byte) 0);
        if (k == (u.gety((byte) 0) / v.gety((byte) 0))) {
            if (k == (u.getz((byte) 0) / v.getz((byte) 0))) {
                ans = true;
            }
        }
        return ans;
    }

    public boolean arePerpendicular(Vecteur u, Vecteur v) {
        boolean ans = false;
        double ps = produitScal(u, v);
        if (ps == 0) {
            ans = true;
        }
        return ans;
    }
}
