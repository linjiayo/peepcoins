import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/watch-list">
        Watch List
      </MenuItem>
      <MenuItem icon="asterisk" to="/crypto-user">
        Crypto User
      </MenuItem>
      <MenuItem icon="asterisk" to="/crypto">
        Crypto
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
