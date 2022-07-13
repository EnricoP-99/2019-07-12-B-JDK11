package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.food.db.FoodDao;
import it.polito.tdp.nyc.model.Vicini;

public class Model {

	FoodDao dao;
	Graph<FoodGrassi,DefaultWeightedEdge> grafo;
	public Model() {
		dao = new FoodDao();
	}
	
	public void creaGrafo(int n)
	{
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(n));
		
		for(FoodGrassi fg1 : this.dao.getVertici(n))
		{
			for(FoodGrassi fg2 : this.dao.getVertici(n))
			{
				if(fg1.getId()!=fg2.getId())
				{
					if(fg1.getGrassi()>fg2.getGrassi())
					{
						double peso= fg1.getGrassi()-fg2.getGrassi();
						if(peso!=0)
						{
							Graphs.addEdgeWithVertices(this.grafo, fg1, fg2, peso);
						}
					}
					else
					{
						double peso= fg2.getGrassi()-fg1.getGrassi();
						if(peso!=0)
						{
							Graphs.addEdgeWithVertices(this.grafo, fg2, fg1, peso);
						}
					}
					
				}
				
			}
		}
	}
	
	public List<FoodGrassi> getVertici(int n){
		return this.dao.getVertici(n);
	}
	
	public boolean grafoCreato()
	{
		if(this.grafo==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public int getNVertici() {
		
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		
		return this.grafo.edgeSet().size();
	}
	
	public List<FoodGrassi> getMingrassi(FoodGrassi fg)
	{
		List<FoodGrassi> min = new ArrayList<>();
		List<FoodGrassi> successori =Graphs.successorListOf(this.grafo, fg);
		for(FoodGrassi g1:successori )
		{
			double peso1 = this.grafo.getEdgeWeight(this.grafo.getEdge(fg, g1));
			for(FoodGrassi g2:successori )
			{
				double peso2 = this.grafo.getEdgeWeight(this.grafo.getEdge(fg, g2));
				//SoodGrassi fd = new FoodGrassi(g.getId(),g.getName(),peso);
				//min.add(fd);
			}
		}
		
		
		return min;
	}
	
	
}
