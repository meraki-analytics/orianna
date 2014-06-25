package lib.orianna.type.staticdata;

import java.io.Serializable;
import java.util.Map;

public class Realm implements Serializable {
    private static final long serialVersionUID = -2293502686486695802L;
    public final String cdn, css, dd, l, lg, store, v;
    public final Map<String, String> n;
    public final Integer profileIconMax;

    public Realm(final String cdn, final String css, final String dd, final String l, final String lg, final String store, final String v,
            final Integer profileIconMax, final Map<String, String> n) {
        this.cdn = cdn;
        this.css = css;
        this.dd = dd;
        this.l = l;
        this.lg = lg;
        this.store = store;
        this.v = v;
        this.profileIconMax = profileIconMax;
        this.n = n;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Realm)) {
            return false;
        }
        final Realm other = (Realm)obj;
        if(cdn == null) {
            if(other.cdn != null) {
                return false;
            }
        }
        else if(!cdn.equals(other.cdn)) {
            return false;
        }
        if(css == null) {
            if(other.css != null) {
                return false;
            }
        }
        else if(!css.equals(other.css)) {
            return false;
        }
        if(dd == null) {
            if(other.dd != null) {
                return false;
            }
        }
        else if(!dd.equals(other.dd)) {
            return false;
        }
        if(l == null) {
            if(other.l != null) {
                return false;
            }
        }
        else if(!l.equals(other.l)) {
            return false;
        }
        if(lg == null) {
            if(other.lg != null) {
                return false;
            }
        }
        else if(!lg.equals(other.lg)) {
            return false;
        }
        if(n == null) {
            if(other.n != null) {
                return false;
            }
        }
        else if(!n.equals(other.n)) {
            return false;
        }
        if(profileIconMax == null) {
            if(other.profileIconMax != null) {
                return false;
            }
        }
        else if(!profileIconMax.equals(other.profileIconMax)) {
            return false;
        }
        if(store == null) {
            if(other.store != null) {
                return false;
            }
        }
        else if(!store.equals(other.store)) {
            return false;
        }
        if(v == null) {
            if(other.v != null) {
                return false;
            }
        }
        else if(!v.equals(other.v)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (cdn == null ? 0 : cdn.hashCode());
        result = prime * result + (css == null ? 0 : css.hashCode());
        result = prime * result + (dd == null ? 0 : dd.hashCode());
        result = prime * result + (l == null ? 0 : l.hashCode());
        result = prime * result + (lg == null ? 0 : lg.hashCode());
        result = prime * result + (n == null ? 0 : n.hashCode());
        result = prime * result + (profileIconMax == null ? 0 : profileIconMax.hashCode());
        result = prime * result + (store == null ? 0 : store.hashCode());
        result = prime * result + (v == null ? 0 : v.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Realm [cdn=" + cdn + "]";
    }

}
