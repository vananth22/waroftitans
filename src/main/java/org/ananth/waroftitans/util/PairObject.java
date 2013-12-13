/**
 * 
 */
package org.ananth.waroftitans.util;

/**
 * @author Ananth
 *
 */
public final class PairObject<L,R> {
    
    
    private L left;
    private R right;
    
    
    private PairObject (final L left, final R right) {
        this.left = left;
        this.right = right;
    }
    
    
    
    public static <L,R> PairObject<L,R> of(final L left, final R right) {
        
        return new PairObject<L, R>(left, right);
        
    }



    public L getLeft () {
        return left;
    }



    public void setLeft (
                         L left) {
        this.left = left;
    }



    public R getRight () {
        return right;
    }



    public void setRight (
                          R right) {
        this.right = right;
    }
    
    
    

}
