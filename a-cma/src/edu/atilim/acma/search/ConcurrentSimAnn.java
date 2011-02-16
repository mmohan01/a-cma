package edu.atilim.acma.search;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import edu.atilim.acma.RunConfig;
import edu.atilim.acma.design.Design;

public class ConcurrentSimAnn extends ConcurrentMultiRunAlgorithm {
	private int iterations;
	
	public ConcurrentSimAnn() {
	}
	
	public ConcurrentSimAnn(String name, RunConfig config, Design initialDesign, int iterations, int runCount) {
		super(name, config, initialDesign, runCount);
		
		this.iterations = iterations;
	}
	
	@Override
	public AbstractAlgorithm spawnAlgorithm() {
		return new SimAnnAlgorithm(new SolutionDesign(getInitialDesign(), getConfig()), null, iterations);
	}
	
	@Override
	public String getRunInfo() {
		return String.format("Simulated Annealing. Iterations: %d.", iterations);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		
		out.writeInt(0); //version
		out.writeInt(iterations);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		super.readExternal(in);
		
		in.readInt();
		iterations = in.readInt();
	}
}
