package io.github.initauther97.arclight.atc.fixer;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.fixes.References;

public class TrinketsEntityFix extends DataFix {
    public TrinketsEntityFix(Schema schema) {
        super(schema, true);
    }

    @Override
    protected TypeRewriteRule makeRule() {
        var in = this.getInputSchema().getType(References.ENTITY);
        var out = this.getOutputSchema().getType(References.ENTITY);
        return this.fixTypeEverywhereTyped("TrinketsEntityFix", in, out, this::createDummyList);
    }

    public Typed<?> createDummyList(Typed<?> raw) {
        System.out.println("Applying TrinketsEntityFix");
        var listType = DSL.optional(DSL.field("cardinal_components", DSL.optional(DSL.field("trinkets:trinkets", DSL.optional(DSL.compoundList(DSL.optional(DSL.compoundList(DSL.optional(DSL.field("Items", getOutputSchema().getType(References.ITEM_STACK)))))))))));
        var list = listType.pointTyped(raw.getOps());
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Cannot create dummy list for trinkets");
        }
        return Typed.pair(list.get(), raw);
    }
}
