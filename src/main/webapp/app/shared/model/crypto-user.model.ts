import { IUser } from 'app/shared/model/user.model';
import { IWatchList } from 'app/shared/model/watch-list.model';

export interface ICryptoUser {
  id?: number;
  internalUser?: IUser | null;
  watchLists?: IWatchList[] | null;
}

export const defaultValue: Readonly<ICryptoUser> = {};
