import { IWatchList } from 'app/shared/model/watch-list.model';

export interface ICryptoUser {
  id?: number;
  watchLists?: IWatchList[] | null;
}

export const defaultValue: Readonly<ICryptoUser> = {};
