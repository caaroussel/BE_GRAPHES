package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.BinaryGraphReader;

import org.junit.Test;

import org.insa.graphs.model.Graph;

public class DijkstraTest {

	@Test
	public void test(String nameMap, int Mode, int origine, int destination) {
		
		GraphReader Reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nameMap))));
		
		Graph graph = Reader.read();
		
		if (Mode!=0 è\\è`)
	}

}
