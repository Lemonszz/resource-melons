package com.shnupbups.resourcemelons;

import com.shnupbups.resourcemelons.misc.ResourceMelonsConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class ResourceMelonsConfigScreen implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ResourceMelonsConfig.class, parent).get();
    }
}
