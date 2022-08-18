import { ICrypto } from 'app/shared/model/crypto.model';
import { ICryptoUser } from 'app/shared/model/crypto-user.model';

export interface IWatchList {
  id?: number;
  cryptos?: ICrypto[] | null;
  cryptoUser?: ICryptoUser | null;
}

export const defaultValue: Readonly<IWatchList> = {};
