package io.github.initauther97.arclight.atc.mixin;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import io.github.initauther97.arclight.atc.fixer.TrinketsEntityFix;
import io.github.initauther97.arclight.atc.fixer.TrinketsPlayerFix;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.EntityPaintingMotiveFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(DataFixers.class)
public class DataFixersMixin {

	@Unique
	private static Schema atc$v1460;

	@Redirect(method = "addFixers", at = @At(value = "NEW", target = "Lnet/minecraft/util/datafix/fixes/EntityPaintingMotiveFix;"))
	private static EntityPaintingMotiveFix atc$redirectCtor(Schema schema, boolean bl) {
		atc$v1460 = schema;
		return new EntityPaintingMotiveFix(schema, bl);
	}

	@Redirect(method = "addFixers", at = @At(value = "INVOKE", ordinal = 0, remap = false,	 target = "Lcom/mojang/datafixers/DataFixerBuilder;addFixer(Lcom/mojang/datafixers/DataFix;)V"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/util/datafix/fixes/EntityPaintingMotiveFix;<init>(Lcom/mojang/datafixers/schemas/Schema;Z)V")))
	private static void atc$addFixer(DataFixerBuilder instance, DataFix fix) {
		instance.addFixer(fix);
		instance.addFixer(new TrinketsEntityFix(atc$v1460));
		instance.addFixer(new TrinketsPlayerFix(atc$v1460));
	}
}