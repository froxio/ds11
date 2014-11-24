public interface State 
{
     public int hashCode();
     public boolean equals(Object anotherState);

     // copies the parameter values to this 
     public void copy(State anotherState);

     // returns a copy of  this 
     public State clone();
} 
