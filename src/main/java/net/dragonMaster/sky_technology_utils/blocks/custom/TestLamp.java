package net.dragonMaster.sky_technology_utils.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class TestLamp extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public TestLamp(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            level.setBlock(blockPos, blockState.cycle(LIT), 3);

            // 3 is if the server or client gets notified, leave this as it is
        }
        return super.use(blockState,level,blockPos,player,hand,blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
