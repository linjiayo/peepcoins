import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import WatchList from './watch-list';
import CryptoUser from './crypto-user';
import Crypto from './crypto';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="watch-list/*" element={<WatchList />} />
        <Route path="crypto-user/*" element={<CryptoUser />} />
        <Route path="crypto/*" element={<Crypto />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
