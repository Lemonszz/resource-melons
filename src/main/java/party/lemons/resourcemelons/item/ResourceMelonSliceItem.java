package party.lemons.resourcemelons.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ResourceMelonSliceItem extends FoodItem
{
	private final Item output;

	public ResourceMelonSliceItem(int var1, float var2, boolean var3, Item output, Settings var4)
	{
		super(var1, var2, var3, var4);
		this.method_7828();

		this.output = output;
	}

	protected void onConsumed(ItemStack var1, World var2, PlayerEntity var3) {
		super.onConsumed(var1, var2, var3);

		var3.dropItem(new ItemStack(output), true);
	}
}
