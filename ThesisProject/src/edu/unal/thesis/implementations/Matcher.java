package edu.unal.thesis.implementations;

import edu.unal.thesis.model.IMatcher;

/**
 * @author fredy
 *
 */
public class Matcher implements IMatcher {
    
    private String pattern;
    
    public Matcher(String pattern) {
        this.pattern = pattern;
    }

    /* (non-Javadoc)
     * @see edu.unal.thesis.implementations.IMatcher#end()
     */
    @Override
    public int end() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see edu.unal.thesis.implementations.IMatcher#find()
     */
    @Override
    public boolean find() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }
    
    /* (non-Javadoc)
     * @see edu.unal.thesis.implementations.IMatcher#group()
     */
    @Override
    public String group() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    /* (non-Javadoc)
     * @see edu.unal.thesis.implementations.IMatcher#start()
     */
    @Override
    public int start() {
        // TODO Auto-generated method stub
        return 0;
    }

}
