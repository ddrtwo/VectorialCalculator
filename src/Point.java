

import macy.IOClass;
//import java.io.*;

public class Point implements Comparable<Point> {
    IOClass inout = new IOClass();
    CoordMan cm = new CoordMan();
    // variables d'état
    private String name;

    // constructeurs
    public Point() {
        byte b = 0;
        cm.setData(0.0, 0.0, 0.0, b);
    }

    public Point(double x, double y, double z, byte polCyl) {
        cm.setData(x, y, z, polCyl);
    }

    public Point(double x, double y, byte polCyl) {
        cm.setData(x, y, 0.0, polCyl);
    }

    // Fin des constructeurs
    // Implémentation de compareTo puisqu'on doit pouvoir comparer...
    public int compareTo(Point p) {
        return (name.compareTo(p.name));
    }

    // Redéfinition de equals
    @Override
    public boolean equals(Object unPoint) {
        Point pt = (Point) unPoint;
        return getName().equals(pt.getName());
    }

    // Redéfinition de hashcode
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String afficherCoord(byte polCyl) {
        String ans;
        ans = "(" + cm.getX(polCyl) + ", " + cm.getY(polCyl) + ", " + cm.getZ(polCyl) + ")";
        return ans;
    }

    public String getName() {
        return name;
    }

    public void setName(String input) {
        name = input;
    }

    @Override
    public String toString() {    // redéfinition de la méthode générique toString
        return name;
    }

    public void afficher(byte polCyl) {
        //System.out.println(this.toString() + "(" + x +"," + y + "," + z + ")");
        inout.afficher("(" + String.format("%.3f", cm.getX(polCyl)) + ";" + String.format("%.3f", cm.getY(polCyl)) + ";" + String.format("%.3f", cm.getZ(polCyl)) + ")");
    }

    public double getx(byte polCyl) {
        return cm.getX(polCyl);
    }

    public double gety(byte polCyl) {
        return cm.getY(polCyl);
    }

    public double getz(byte polCyl) {
        return cm.getZ(polCyl);
    }

    public void setx(double entry, byte polCyl) {
        byte b = 0;
        cm.setSingleData(entry, b, polCyl);
    }

    public void sety(double entry, byte polCyl) {
        byte b = 1;
        cm.setSingleData(entry, b, polCyl);
    }

    public void setz(double entry, byte polCyl) {
        byte b = 2;
        cm.setSingleData(entry, b, polCyl);
    }

    public void homothetie(double dx, double dy) {
        Double x, y, z;
        byte b = 0;
        x = cm.getX(b) + dx;
        y = cm.getY(b) + dy;
        z = 0.0;
        cm.setData(x, y, z, b);
    }

    public void homothetie(double dx, double dy, double dz) {
        Double x, y, z;
        byte b = 0;
        x = cm.getX(b) + dx;
        y = cm.getY(b) + dy;
        z = cm.getZ(b) + dz;
        cm.setData(x, y, z, b);
    }

    public void rotation(double alpha) {
        Double rho, theta, phi;
        byte b = 2;    // coordonnées polaires
        rho = cm.getX(b);
        theta = cm.getY(b) + alpha;
        phi = cm.getZ(b);
        cm.setData(rho, theta, phi, b);
    }

    public void rotation(double alpha, double beta) {
        Double rho, theta, phi;
        byte b = 2;    // coordonnées polaires
        rho = cm.getX(b);
        theta = cm.getY(b) + alpha;
        phi = cm.getZ(b) + beta;
        cm.setData(rho, theta, phi, b);
    }

    class CoordMan {
        private Double[][] coord = new Double[3][3];

        public void setData(Double x, Double y, Double z, byte i) {
            if (i > 2) {
                inout.afficher("Mauvais format de type de coordonnées utilisé");
            } else {
                coord[i][0] = x;
                coord[i][1] = y;
                coord[i][2] = z;
                switch (i) {
                    case 0:    // coordonnées cartésiennes
                        coord[1][0] = Math.sqrt(x * x + y * y);
                        coord[1][1] = Math.atan2(y, x);    // ceci c'est theta, compris entre -pi et pi
                        coord[1][2] = z;
                        coord[2][0] = Math.sqrt(x * x + y * y + z * z);
                        coord[2][1] = Math.atan2(y, x);    // ceci c'est theta, compris entre -pi et pi
                        coord[2][2] = Math.atan(z / Math.sqrt(x * x + y * y)); // phi est compris entre +/-pi demi
                        break;
                    case 1:    // coordonnées cylindriques
                        coord[0][0] = x * Math.cos(y); // x contient le rho du plan oxy
                        coord[0][1] = x * Math.sin(y);
                        coord[0][2] = z;
                        coord[2][0] = Math.sqrt(x * x + z * z);
                        coord[2][1] = y;    // le théta est inchangé
                        coord[2][2] = Math.atan(z / x); // phi est compris entre +/-pi demi
                        break;
                    case 2:    // coordonnées polaires
                        Double tmp = x * Math.cos(z); // ceci contient le rho du plan oxy
                        coord[0][0] = tmp * Math.cos(y);
                        coord[0][1] = tmp * Math.sin(y);
                        coord[0][2] = x * Math.sin(z); // phi est compris entre +/-pi demi donc on trouve le z
                        coord[1][0] = x * Math.cos(z);
                        coord[1][1] = y;    // le théta est inchangé
                        coord[1][2] = x * Math.sin(z); // z est phi et doit être compris entre +/-pi demi
                        break;
                    default:
                        break;
                }
            }
        }

        public void setSingleData(Double data, byte position, byte polCyl) {
            Double x, y, z;
            x = coord[polCyl][0];
            y = coord[polCyl][1];
            z = coord[polCyl][2];
            if (polCyl > 2) {
                inout.afficher("Mauvais format de type de coordonnées utilisé");
            } else {
                switch (position) {
                    case 0:
                        x = data;
                        break;
                    case 1:
                        y = data;
                        break;
                    case 2:
                        z = data;
                        break;
                    default:
                        break;
                }
                setData(x, y, z, polCyl);
            }
        }

        public Double[] getData(byte i) {
            if (i > 2) {
                inout.afficher("Mauvais format de type de coordonnées!");
                return null;
            }
            return coord[i];
        }

        public Double getX(byte i) {
            return coord[i][0];
        }

        public Double getY(byte i) {
            return coord[i][1];
        }

        public Double getZ(byte i) {
            return coord[i][2];
        }
    }
}
