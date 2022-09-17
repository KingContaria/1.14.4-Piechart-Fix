package com.kingcontaria.piechartfix;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.ProfilerTiming;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @ModifyConstant(method = "handleProfilerKeyPress", constant = @Constant(stringValue = "."))
    private String fixPieChart(String value) {
        return "\u001e";
    }

    @Redirect(method = "drawProfilerResults", at = @At(value = "FIELD", target = "Lnet/minecraft/util/profiler/ProfilerTiming;name:Ljava/lang/String;", opcode = Opcodes.GETFIELD, ordinal = 2))
    private String getHumanReadableName(ProfilerTiming profilerTiming) {
        return profilerTiming.name.replace('\u001e', '.');
    }

}
