package com.weareadaptive.nexus.casc.plugin.internal.config;

import org.sonatype.nexus.ldap.persist.internal.LdapConfigurationData;

import java.util.List;

public class Config {
    private ConfigCore core;
    private ConfigRepository repository;
    private ConfigSecurity security;
    private List<ConfigCapability> capabilities;
    private List<LdapConfigurationData> ldapConfigs;

    public ConfigCore getCore() {
        return core;
    }

    public void setCore(ConfigCore core) {
        this.core = core;
    }

    public ConfigRepository getRepository() {
        return repository;
    }

    public void setRepository(ConfigRepository repository) {
        this.repository = repository;
    }

    public ConfigSecurity getSecurity() {
        return security;
    }

    public void setSecurity(ConfigSecurity security) {
        this.security = security;
    }

    public List<ConfigCapability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<ConfigCapability> capabilities) {
        this.capabilities = capabilities;
    }

    public List<LdapConfigurationData> getLdapConfigs() { return ldapConfigs; }

    public void setLdapConfigs(List<LdapConfigurationData> ldapConfigs) { this.ldapConfigs = ldapConfigs; }
}
