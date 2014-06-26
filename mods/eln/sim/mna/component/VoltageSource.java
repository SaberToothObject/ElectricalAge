package mods.eln.sim.mna.component;

import mods.eln.INBTTReady;
import mods.eln.sim.mna.SubSystem;
import mods.eln.sim.mna.misc.ISubSystemProcessI;
import mods.eln.sim.mna.state.CurrentState;
import mods.eln.sim.mna.state.State;

import org.apache.commons.math3.linear.RealMatrix;


public class VoltageSource extends Bipole implements ISubSystemProcessI{

	
	public VoltageSource() {
		// TODO Auto-generated constructor stub
	}
	
	public VoltageSource(State aPin,State bPin) {
		super(aPin, bPin);
	}
	
	
	double u = 0;
	CurrentState currentState = new CurrentState();
	
	public VoltageSource setU(double u) {
		this.u = u;
		return this;
	}
	
	public double getU() {
		return u;
	}
	
	@Override
	public void quitSubSystem() {
		subSystem.states.remove(currentState);
		subSystem.removeProcess(this);
		super.quitSubSystem();
	}
	
	@Override
	public void addedTo(SubSystem s) {
		super.addedTo(s);
		s.addState(currentState);
		s.addProcess(this);
	}
	@Override
	public void applyTo(SubSystem s) {
		s.addToA(aPin,currentState,1.0);
		s.addToA(bPin,currentState,-1.0);
		s.addToA(currentState,aPin,1.0);
		s.addToA(currentState,bPin,-1.0);
	}


	@Override
	public void simProcessI(SubSystem s) {
		s.addToI(currentState, u);
		
	}

	public double getI() {
		// TODO Auto-generated method stub
		return -currentState.state;
	}
	
	
	@Override
	public double getCurrent() {
		// TODO Auto-generated method stub
		return -currentState.state;
	}
	

}
