package net.dragonMaster.sky_technology_utils;
import appeng.api.networking.pathing.ChannelMode;
import appeng.core.AEConfig;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.capability.IMiner;
import com.gregtechceu.gtceu.api.capability.PlatformEnergyCompat;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.DrumMachineItem;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.*;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredPartMachine;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.machine.steam.SteamBoilerMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.client.TooltipHelper;
import com.gregtechceu.gtceu.client.renderer.machine.*;
import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import com.gregtechceu.gtceu.common.machine.electric.*;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.*;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeCombustionEngineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.generator.LargeTurbineMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.*;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.CokeOvenMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitivePumpMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.LargeBoilerMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.common.machine.steam.SteamLiquidBoilerMachine;
import com.gregtechceu.gtceu.common.machine.steam.SteamMinerMachine;
import com.gregtechceu.gtceu.common.machine.steam.SteamSolidBoilerMachine;
import com.gregtechceu.gtceu.common.machine.storage.*;
import com.gregtechceu.gtceu.common.pipelike.fluidpipe.longdistance.LDFluidEndpointMachine;
import com.gregtechceu.gtceu.common.pipelike.item.longdistance.LDItemEndpointMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.integration.ae2.GTAEMachines;
import com.gregtechceu.gtceu.integration.kjs.GTRegistryObjectBuilderTypes;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.LDLib;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.api.registry.GTRegistries.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.MACHINE;
import static com.gregtechceu.gtceu.common.data.GTMaterials.DrillingFluid;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.STEAM_BOILER_RECIPES;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toRomanNumeral;
public class GtPorts {

        /*public final static MachineDefinition[] ITEM_IMPORT_BUS = registerTieredMachines("input_bus",
                (holder, tier) -> new ItemBusPartMachine(holder, tier, IO.IN),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Input Bus")
                        .rotationState(RotationState.ALL)
                        .abilities(tier == 0 ? new PartAbility[] {PartAbility.IMPORT_ITEMS, PartAbility.STEAM_IMPORT_ITEMS} : new PartAbility[]{PartAbility.IMPORT_ITEMS})
                        .overlayTieredHullRenderer("item_bus.import")
                        .tooltips(Component.translatable("gtceu.machine.item_bus.import.tooltip"),
                                Component.translatable("gtceu.universal.tooltip.item_storage_capacity", (1 + Math.min(9, tier))*(1 + Math.min(9, tier))))
                        .compassNode("item_bus")
                        .register(),
                ALL_TIERS);

        public final static MachineDefinition[] ITEM_EXPORT_BUS = registerTieredMachines("output_bus",
                (holder, tier) -> new ItemBusPartMachine(holder, tier, IO.OUT),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Output Bus")
                        .rotationState(RotationState.ALL)
                        .abilities(tier == 0 ? new PartAbility[] {PartAbility.EXPORT_ITEMS, PartAbility.STEAM_EXPORT_ITEMS} : new PartAbility[]{PartAbility.EXPORT_ITEMS})
                        .overlayTieredHullRenderer("item_bus.export")
                        .tooltips(Component.translatable("gtceu.machine.item_bus.export.tooltip"),
                                Component.translatable("gtceu.universal.tooltip.item_storage_capacity", (1 + Math.min(9, tier))*(1 + Math.min(9, tier))))
                        .compassNode("item_bus")
                        .register(),
                ALL_TIERS);

        public final static MachineDefinition[] FLUID_IMPORT_HATCH = registerTieredMachines("input_hatch",
                (holder, tier) -> new FluidHatchPartMachine(holder, tier, IO.IN),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Input Hatch")
                        .rotationState(RotationState.ALL)
                        .abilities(PartAbility.IMPORT_FLUIDS)
                        .overlayTieredHullRenderer("fluid_hatch.import")
                        .tooltips(Component.translatable("gtceu.machine.fluid_hatch.import.tooltip"),
                                Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                        .compassNode("fluid_hatch")
                        .register(),
                ALL_TIERS);

        public final static MachineDefinition[] FLUID_EXPORT_HATCH = registerTieredMachines("output_hatch",
                (holder, tier) -> new FluidHatchPartMachine(holder, tier, IO.OUT),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Output Hatch")
                        .rotationState(RotationState.ALL)
                        .abilities(PartAbility.EXPORT_FLUIDS)
                        .overlayTieredHullRenderer("fluid_hatch.export")
                        .tooltips(Component.translatable("gtceu.machine.fluid_hatch.export.tooltip"),
                                Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", (8 * FluidHelper.getBucket()) * (1L << Math.min(9, tier))))
                        .compassNode("fluid_hatch")
                        .register(),
                ALL_TIERS);

        public final static MachineDefinition[] ENERGY_INPUT_HATCH = registerTieredMachines("energy_input_hatch",
                (holder, tier) -> new EnergyHatchPartMachine(holder, tier, IO.IN, 2),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Energy Hatch")
                        .rotationState(RotationState.ALL)
                        .abilities(PartAbility.INPUT_ENERGY)
                        .overlayTieredHullRenderer("energy_hatch.input")
                        .compassNode("energy_hatch")
                        .register(),
                ALL_TIERS);

        public final static MachineDefinition[] ENERGY_OUTPUT_HATCH = registerTieredMachines("energy_output_hatch",
                (holder, tier) -> new EnergyHatchPartMachine(holder, tier, IO.OUT, 2),
                (tier, builder) -> builder
                        .langValue(VNF[tier] + " Dynamo Hatch")
                        .rotationState(RotationState.ALL)
                        .abilities(PartAbility.OUTPUT_ENERGY)
                        .overlayTieredHullRenderer("energy_hatch.output")
                        .compassNode("energy_hatch")
                        .register(),
                ALL_TIERS);*/
        public void GtPortsCreation(){
        REGISTRATE.machine("Test item port", (holder) -> new GtOverrides(holder, GTValues.ULV, 2, IO.IN));
    }




}

