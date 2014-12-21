package org.treetest.dto;

import java.util.List;

public interface Node {
	public String getName();
	public List<Node> getChildren();
}
