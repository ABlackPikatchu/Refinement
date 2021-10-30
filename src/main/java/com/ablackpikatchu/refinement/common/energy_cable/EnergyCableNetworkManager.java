package com.ablackpikatchu.refinement.common.energy_cable;

import static com.ablackpikatchu.refinement.Refinement.LOGGER;
import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import net.minecraftforge.common.util.Constants;

public class EnergyCableNetworkManager extends WorldSavedData {
	
	private static final String NAME = MOD_ID + "_energy_cable_networks";

	private final World world;
	
	private final Map<String, EnergyCableNetwork> networks = new HashMap<>();
	
	public EnergyCableNetworkManager(String name, World world) {
		super(name);
		this.world = world;
	}
	
	public static EnergyCableNetworkManager get(World world) {
        return get((ServerWorld) world);
    }

    public static EnergyCableNetworkManager get(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(() -> new EnergyCableNetworkManager(NAME, world), NAME);
    }
    
    public void addNetwork(EnergyCableNetwork network) {
        if (networks.containsKey(network.getId())) {
            throw new RuntimeException("Duplicate Energy Cable Network " + network.getId());
        }

        networks.put(network.getId(), network);

        LOGGER.debug("Energy Cable Network {} created", network.getId());

        setDirty();
    }
    
    public void removeNetwork(String id) {
        if (!networks.containsKey(id)) {
            throw new RuntimeException("Energy Cable Network " + id + " not found");
        }

        networks.remove(id);

        LOGGER.debug("Energy Cable Network {} removed", id);

        setDirty();
    }
    
    public Collection<EnergyCableNetwork> getNetworks() {
        return networks.values();
    }

	@Override
	public void load(CompoundNBT tag) {
        ListNBT nets = tag.getList("networks", Constants.NBT.TAG_COMPOUND);
        for (INBT netTag : nets) {
            CompoundNBT netTagCompound = (CompoundNBT) netTag;
            EnergyCableNetwork network = EnergyCableNetwork.readFromNbt(netTagCompound);
            networks.put(network.getId(), network);
        }

        LOGGER.debug("Read {} energy cable networks", networks.size());
	}

	@Override
	public CompoundNBT save(CompoundNBT tag) {
        ListNBT newNetworks = new ListNBT();
        this.networks.values().forEach(n -> {
            CompoundNBT networkTag = new CompoundNBT();
            newNetworks.add(n.writeToNbt(networkTag));
        });
        tag.put("networks", newNetworks);
        return tag;
	}
	
	public World getWorld() {
		return world;
	}

	public static class NetworkException extends Exception {
		private static final long serialVersionUID = 2767479974752249900L;
		
		public NetworkException() {
			super();
		}

		public NetworkException(String message) {
			super(message);
		}

		public NetworkException(String message, Throwable cause) {
			super(message, cause);
		}

		public NetworkException(Throwable cause) {
			super(cause);
		}
	}

}
