package com.ablackpikatchu.refinement.common.item;

import com.ablackpikatchu.refinement.api.item.RefinementItem;
import com.ablackpikatchu.refinement.common.te.tier.ITieredTile;
import com.ablackpikatchu.refinement.common.te.tier.Tier;

import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class TierUpgraderItem extends RefinementItem {
	
	public final Tier tier;

	public TierUpgraderItem(Tier tier, Properties pProperties) {
		super(pProperties);
		this.tier = tier;
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext pContext) {
		if (!pContext.getLevel().isClientSide()) {
			if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof ITieredTile) {
				ITieredTile tieredTile = (ITieredTile) pContext.getLevel().getBlockEntity(pContext.getClickedPos());
				if (tieredTile.getCurrentTier().nextTier() != null && tieredTile.getCurrentTier().nextTier() == this.tier) {
					tieredTile.setTier(this.tier);
					if (!pContext.getPlayer().isCreative())
						pContext.getItemInHand().shrink(1);
					return ActionResultType.SUCCESS;
				}
			}
		}
		return super.useOn(pContext);
	}

}
