package com.sqlgen.dao;

import java.io.Serializable;

public abstract class DefaultDAO implements Serializable, Cloneable{
	
		private static final long serialVersionUID = 1L;

		/**
	     * Clones the object using serialization
	     * 
	     * @see java.lang.Object#clone()
	     * @see SerializationUtils#clone(java.io.Serializable)
	     */
	    @Override
	    public Object clone() throws CloneNotSupportedException {
		return super.clone();
	    }

	    /**
	     * It reuses EqualsBuilder#reflectionEquals(Object, Object) to build equals
	     * 
	     * @see java.lang.Object#equals(java.lang.Object)
	     * @see EqualsBuilder#reflectionEquals(Object, Object)
	     */
	    @Override
	    public boolean equals(Object obj) {
		//return EqualsBuilder.reflectionEquals(this, obj);
	    return true;
	    }

	    /**
	     * 
	     * @see java.lang.Object#hashCode()
	     * @see HashCodeBuilder#reflectionHashCode(Object)
	     */
	    @Override
	    public int hashCode() {
		//return HashCodeBuilder.reflectionHashCode(this);
	    return 0;
	    }

	    /**
	     * Shows comments of object in default style
	     * 
	     * @see java.lang.Object#toString()
	     * @see ToStringBuilder#reflectionToString(Object, ToStringStyle)
	     */
	    @Override
	    public String toString() {
		//return ToStringBuilder.reflectionToString(this,
			//ToStringStyle.DEFAULT_STYLE);
	    	return "";
	    }
}
