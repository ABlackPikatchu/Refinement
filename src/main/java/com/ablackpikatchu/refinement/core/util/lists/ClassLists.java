package com.ablackpikatchu.refinement.core.util.lists;

import java.util.ArrayList;

import com.ablackpikatchu.refinement.core.init.BlockInit;
import com.ablackpikatchu.refinement.core.init.BlockItemInit;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.google.common.collect.Lists;

public class ClassLists {
	
	public static final ArrayList<Class<?>> REGISTRIES_CLASSES = Lists.newArrayList(BlockInit.class, BlockItemInit.class, ItemInit.class);

}
