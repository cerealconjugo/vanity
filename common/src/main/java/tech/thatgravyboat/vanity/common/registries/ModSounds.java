package tech.thatgravyboat.vanity.common.registries;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import tech.thatgravyboat.vanity.common.Vanity;

import java.util.function.Supplier;

public class ModSounds {

    public static final ResourcefulRegistry<SoundEvent> SOUNDS = ResourcefulRegistries.create(BuiltInRegistries.SOUND_EVENT, Vanity.MOD_ID);

    public static final Supplier<SoundEvent> TAKE_RESULT_STYLING_TABLE = SOUNDS.register("ui.styling_table.take_result", () -> SoundEvent.createVariableRangeEvent(Vanity.id("ui.styling_table.take_result")));
    public static final Supplier<SoundEvent> OPEN_DESIGN = SOUNDS.register("item.design.open", () -> SoundEvent.createVariableRangeEvent(Vanity.id("item.design.open")));
}
